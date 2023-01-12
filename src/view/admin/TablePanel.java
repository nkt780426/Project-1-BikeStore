package view.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
abstract class TablePanel<T> extends JPanel {
	private AdminFrameView adminFrameView;
	private JTable table;
	private JButton btnNew;
	private JButton btnDelete;
	private ArrayList<T> data;
	private DefaultTableModel tblModel;
	private JTextField txtFilter;

	public TablePanel(AdminFrameView adminFrameView) {
		this.adminFrameView = adminFrameView;
		initComponent();
	}

	public abstract void setEvent();

	public abstract void initTable();

	public abstract void loadDataToTable();

	private void initComponent() {
		// component
		Font font = new Font("Arial", Font.PLAIN, 16);
		// Filter

		JLabel lbFilter = new JLabel("FILTER");
		lbFilter.setFont(font);

		txtFilter = new JTextField();
		txtFilter.setFont(font);

		// table
		table = new JTable() {
			// khong cho phep Jtable duoc sua
			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			}
		};
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// btn
		btnNew = new JButton("New", new ImageIcon(getClass().getResource("/icon/new-icon16.png")));
		btnNew.setFont(font);
		btnDelete = new JButton("Delete", new ImageIcon(getClass().getResource("/icon/Button-Close-icon16.png")));
		btnDelete.setFont(font);
		// layout
		setLayout(new BorderLayout());
		// Page_Start
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		panelSearch.add(lbFilter, gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panelSearch.add(txtFilter, gbc);

		add(panelSearch, BorderLayout.PAGE_START);
		// Center
		add(scrollPane, BorderLayout.CENTER);
		// Page_end
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelButton.add(btnNew);
		panelButton.add(btnDelete);
		add(panelButton, BorderLayout.PAGE_END);
	}

	// sắp xếp và lọc table sử dụng sorter và filter
	public void sortAndFilter(String target) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblModel);
		table.setRowSorter(sorter);

		sorter.setRowFilter(RowFilter.regexFilter(target));
		// regexFilter (String, int index): tìm kiếm string từ cột có chỉ số index(index
		// =0,...) nếu không có index mặc đinh JTable tìm tất cả các column
		// nếu chỉ muốn tìm trên 1 số cột nhất định
		// ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter>(số
		// column);
		// filters.add(RowFilter.regexFilter(String, index1));
		// filters.add(RowFIlter.regexFIlter (String, index2));
		// RowFilter <Object,Object> rf = RowFilter.andFilter (filters);
		// sorter.setRowFilter(rf);
	}

	public AdminFrameView getAdminFrameView() {
		return adminFrameView;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnNew() {
		return btnNew;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public ArrayList<T> getData() {
		return data;
	}

	public DefaultTableModel getTblModel() {
		return tblModel;
	}

	public JTextField getTxtFilter() {
		return txtFilter;
	}

	public void setAdminFrameView(AdminFrameView adminFrameView) {
		this.adminFrameView = adminFrameView;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setBtnNew(JButton btnNew) {
		this.btnNew = btnNew;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void setData(ArrayList<T> data) {
		this.data = data;
	}

	public void setTblModel(DefaultTableModel tblModel) {
		this.tblModel = tblModel;
	}

	public void setTxtFilter(JTextField txtFilter) {
		this.txtFilter = txtFilter;
	}

}
