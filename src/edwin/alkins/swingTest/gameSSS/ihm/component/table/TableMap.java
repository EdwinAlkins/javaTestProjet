package edwin.alkins.swingTest.gameSSS.ihm.component.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableMap extends AbstractTableModel implements TableModelListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5049480887345362406L;
	private TableModel model;

    public TableModel getModel() {
        return model;
    }

    public void setModel(TableModel model) {
        if (this.model != null) {
            this.model.removeTableModelListener(this);
        }
        this.model = model;
        if (this.model != null) {
            this.model.addTableModelListener(this);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
        return model.getColumnClass(column);
    }

    public int getColumnCount() {
        return ((model == null) ? 0 : model.getColumnCount());
    }

    public String getColumnName(int column) {
        return model.getColumnName(column);
    }

    public int getRowCount() {
        return ((model == null) ? 0 : model.getRowCount());
    }

    public Object getValueAt(int row, int column) {
        return model.getValueAt(row, column);
    }

    public void setValueAt(Object value, int row, int column) {
        model.setValueAt(value, row, column);
    }

    public boolean isCellEditable(int row, int column) {
        return model.isCellEditable(row, column);
    }

    public void tableChanged(TableModelEvent tableModelEvent) {
        fireTableChanged(tableModelEvent);
    }
}