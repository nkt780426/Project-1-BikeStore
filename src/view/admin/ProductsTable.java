package view.admin;

import javax.swing.table.DefaultTableModel;

import controller.admin.ProductsTableController;
import dao.ProductsDao;
import model.Products;

@SuppressWarnings("serial")
public class ProductsTable extends TablePanel<Products> {

	public ProductsTable(AdminFrameView adminFrameView) {
		super(adminFrameView);
		initTable();
		loadDataToTable();
		setEvent();
	}

	// JTable có thể implement TableModelListener, TableColumnModelListener,
	// ListSelectionListener, CellSelectionLitener và RowSorterListener
	// Phân biệt ActionListener và DocumentListener:
	// http://www.herongyang.com/Swing/JTextField-ActionListener-and-DocumentListener.html

	public void setEvent() {
		ProductsTableController productsTableController = new ProductsTableController(this, super.getAdminFrameView());
		super.getBtnNew().addActionListener(productsTableController);
		super.getBtnDelete().addActionListener(productsTableController);
		super.getTxtFilter().getDocument().addDocumentListener(productsTableController);
		super.getTable().addMouseListener(productsTableController);
	}

	public void initTable() {
		super.setTblModel(new DefaultTableModel());
		super.getTblModel().setColumnIdentifiers(
				new String[] { "Product ID", "Name", "Brand Id", "Category ID", "Model Year", "List Price" });
		super.getTable().setModel(super.getTblModel());
	}

	public void loadDataToTable() {
		ProductsDao productsDao = new ProductsDao();
		super.setData(productsDao.selectAll());
		super.getTblModel().setRowCount(0);
		for (Products product : super.getData()) {
			super.getTblModel().addRow(new Object[] { product.getProductId(), product.getProductName(),
					product.getBrandId(), product.getCategoryId(), product.getModelYear(), product.getListPrice() });
		}
	}

}
