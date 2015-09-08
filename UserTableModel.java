import javax.swing.table.AbstractTableModel;
import java.util.*;

public class UserTableModel extends AbstractTableModel
{
	private Vector<User> userList = new Vector<User>();
	private String[] columnNames = {"Username",
									"Nickname",
									"Type",
									"Approved"};
	
	public UserTableModel(Vector<User> users) {
		userList = users;
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return userList.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
		switch(col)
		{
			case 0:
				return userList.elementAt(row).getUsername();
			case 1:
				return userList.elementAt(row).getNickname();
			case 2:
				return userList.elementAt(row).getAccessLevel().toString();
			case 3:
				return !userList.elementAt(row).isPending();
			default:
				return null;
		}
	}
	
	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell.  If we didn't implement this method,
	 * then the first column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col < 3) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setValueAt(Object value, int row, int col) {
		switch(col)
		{
			case 0:
				userList.elementAt(row).setUsername((String)value);
			case 1:
				userList.elementAt(row).setNickname((String)value);
			case 2:
				userList.elementAt(row).setAccessLevel((User.AccessLevel)value);
			case 3:
				userList.elementAt(row).setApproved((boolean)value);
		}
		
		fireTableCellUpdated(row, col);
	}
}