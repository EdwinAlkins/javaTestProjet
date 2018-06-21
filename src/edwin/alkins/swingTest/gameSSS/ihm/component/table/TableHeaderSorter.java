package edwin.alkins.swingTest.gameSSS.ihm.component.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TableHeaderSorter extends MouseAdapter {

	private TableSorter sorter;
	private JTable table;
	private boolean ascending;
	
	public TableHeaderSorter() {
		this.ascending = true;
	}
	public void install(TableSorter sorter, JTable table) {
		TableHeaderSorter tableHeaderSorter = new TableHeaderSorter();
		tableHeaderSorter.sorter = sorter;
		tableHeaderSorter.table = table;
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.addMouseListener(tableHeaderSorter);
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		TableColumnModel columnModel = table.getColumnModel();
		int viewColumn = columnModel.getColumnIndexAtX(mouseEvent.getX());
		int column = table.convertColumnIndexToModel(viewColumn);
		if (mouseEvent.getClickCount() == 1 && column != -1) {
			this.ascending = !this.ascending;
			sorter.sortByColumn(column, ascending);
		}
	}
}
