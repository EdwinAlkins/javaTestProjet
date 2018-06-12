package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

//CTRL + SHIFT + O pour générer les imports nécessaires
public class FenetreTestV1 extends JFrame {
  private JTree arbre;
  private DefaultMutableTreeNode racine;
  private DefaultTreeModel model;
  private JButton bouton = new JButton("Ajouter");

  public FenetreTestV1(){
    this.setSize(200, 300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setTitle("JTree");
    //On invoque la méthode de construction de l'arbre

    listRoot();
    bouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event) {
        if(arbre.getLastSelectedPathComponent() != null){                  
          JOptionPane jop = new JOptionPane();
          String nodeName = jop.showInputDialog("Saisir un nom de noeud");
               
          if(nodeName != null && !nodeName.trim().equals("")){                  
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)arbre.getLastSelectedPathComponent();
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(nodeName);
            parentNode.add(childNode);
            model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
            model.nodeChanged(parentNode);                  
          }               
        }
        else{
          System.out.println("Aucune sélection !");
        }
      }
    });
    this.getContentPane().add(bouton, BorderLayout.SOUTH);
    this.setVisible(true);
  }
   
  private void listRoot(){
    this.racine = new DefaultMutableTreeNode();

    int count = 0;
    for(File file : File.listRoots())
    {
      DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
      try {
        for(File nom : file.listFiles()){
          DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"\\");               
          lecteur.add(this.listFile(nom, node));               
        }
      } catch (NullPointerException e) {}
         
      this.racine.add(lecteur);         
    }
    //Nous créons, avec notre hiérarchie, un arbre
    arbre = new JTree();
    this.model = new DefaultTreeModel(this.racine);      
    arbre.setModel(model);
    arbre.setRootVisible(false);
    arbre.setEditable(true);
    arbre.getModel().addTreeModelListener(new TreeModelListener() {
      public void treeNodesChanged(TreeModelEvent evt) {
        System.out.println("Changement dans l'arbre");
        Object[] listNoeuds = evt.getChildren();
        int[] listIndices = evt.getChildIndices();
        for (int i = 0; i < listNoeuds.length; i++) {
          System.out.println("Index " + listIndices[i] + ", noeud déclencheur : " + listNoeuds[i]);
        }
      }   
      public void treeNodesInserted(TreeModelEvent event) {
        System.out.println("Un noeud a été inséré !");            
      }
      public void treeNodesRemoved(TreeModelEvent event) {
        System.out.println("Un noeud a été retiré !");
      }
      public void treeStructureChanged(TreeModelEvent event) {
        System.out.println("La structure d'un noeud a changé !");
      }
    });

    this.getContentPane().add(new JScrollPane(arbre), BorderLayout.CENTER);
  }

  private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
    int count = 0;      
    if(file.isFile())
      return new DefaultMutableTreeNode(file.getName());
    else{
      File[] list = file.listFiles();
      if(list == null)
        return new DefaultMutableTreeNode(file.getName());

      for(File nom : list){
        count++;
        //Pas plus de 5 enfants par noeud
        if(count < 3){
          DefaultMutableTreeNode subNode;
          if(nom.isDirectory()){
            subNode = new DefaultMutableTreeNode(nom.getName()+"\\");
            node.add(this.listFile(nom, subNode));
          }else{
            subNode = new DefaultMutableTreeNode(nom.getName());
          }
          node.add(subNode);
        }
      }
      return node;
    }
  }

  public static void main(String[] args){
    //Nous allons créer des fenêtres avec des look and feel différents 
    //Cette instruction permet de récupérer tous les look and feel du système

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (InstantiationException e) {}
    catch (ClassNotFoundException e) {}
    catch (UnsupportedLookAndFeelException e) {}
    catch (IllegalAccessException e) {}
    FenetreTestV1 fen = new FenetreTestV1();

  }
}