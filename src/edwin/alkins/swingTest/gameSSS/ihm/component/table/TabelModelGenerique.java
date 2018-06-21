package edwin.alkins.swingTest.gameSSS.ihm.component.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;

@SuppressWarnings("serial")
public class TabelModelGenerique extends AbstractTableModel{

	private static final String EDIT = "EDIT";
	private List<BasicObjectCore> rows;
    private List<String> columnHeader;
    private Map<String,String> headerView = new HashMap<>();

	public TabelModelGenerique() {
		this.rows = new ArrayList<>();
		this.columnHeader = new ArrayList<>();
	}

	public Object getValueAt(int row, int column) {
		if(column == -1)
			return this.rows.get(row);
		return this.rows.get(row).getValue(this.columnHeader.get(column));
	}

	public void addHeader(String head){
		columnHeader.add(head);
		this.headerView.put(head, head);
	}

	public int getColumnCount() { 
		return this.columnHeader.size();
	} 

	public String getColumnName(int column){ 
		return this.headerView.get(this.columnHeader.get(column)); 
	} 
	
	public int getColumnid(String column){ 
		int count = 0;
		for(String h:columnHeader) {
			if(h.equals(column))
				return count;
			count++;
		}
		return 0;
	} 

	public int getRowCount() { 
		return this.rows.size();
	} 
	public boolean isCellEditable(int row, int column){
		if(this.getColumnName(column).equals(EDIT)){
			return true;
		}
		return false;
	}  
	
	public synchronized void setData(ArrayList<BasicObjectCore> data) {
        this.rows = new ArrayList<>();
        if(!data.isEmpty()) {
        	for(String header:data.get(1).getHeader()) {
        		this.columnHeader.add(header);
        		this.headerView.put(header, header);
        	}
        }
        for (BasicObjectCore element: data)
            this.rows.add(element);
        fireTableDataChanged();
    }
}