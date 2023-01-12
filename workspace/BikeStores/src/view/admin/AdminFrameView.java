package view.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.admin.AdminFrameController;

@SuppressWarnings("serial")
public class AdminFrameView extends JFrame {
	private JButton btnProducts;
	private JButton btnStaffs;
	private JButton btnCustomers;
	private JButton btnOrders;
	private JButton btnStores;
	private JButton btnBrands;
	private JButton btnCategories;
	private JButton btnSignOut;

	private ProductsTable products;
	private StaffsTable staffs;
	private CustomersTable customers;
	private StoresTable stores;
	private OrdersTable orders;
	private BrandsTable brands;
	private CategoriesTable categories;

	private CardLayout cardLayout;
	private JPanel panelCenter;

	public AdminFrameView() {
		initComnponent();
		setPreferredSize(new Dimension(1000, 700));
		setTitle("Adminstrator UI");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComnponent() {
		// component
		// toolbar
		JLabel lbBikeStores = new JLabel("BIKE STORES", JLabel.CENTER);
		lbBikeStores.setFont(new Font("Arial", Font.BOLD, 40));
		Font font = new Font("Arial", Font.BOLD, 16);
		btnProducts = new JButton("Products");
		btnProducts.setFont(font);
		btnProducts.setHorizontalTextPosition(JButton.CENTER);
		btnProducts.setVerticalTextPosition(JButton.BOTTOM);
		btnProducts.setIcon(new ImageIcon(getClass().getResource("/icon/Bicycle-icon72.png")));

		btnStaffs = new JButton("Staffs");
		btnStaffs.setFont(font);
		btnStaffs.setHorizontalTextPosition(JButton.CENTER);
		btnStaffs.setVerticalTextPosition(JButton.BOTTOM);
		btnStaffs.setIcon(new ImageIcon(getClass().getResource("/icon/Preppy-icon72.png")));

		btnCustomers = new JButton("Customers");
		btnCustomers.setFont(font);
		btnCustomers.setHorizontalTextPosition(JButton.CENTER);
		btnCustomers.setVerticalTextPosition(JButton.BOTTOM);
		btnCustomers.setIcon(new ImageIcon(getClass().getResource("/icon/person72.png")));

		btnOrders = new JButton("Orders");
		btnOrders.setFont(font);
		btnOrders.setHorizontalTextPosition(JButton.CENTER);
		btnOrders.setVerticalTextPosition(JButton.BOTTOM);
		btnOrders.setIcon(new ImageIcon(getClass().getResource("/icon/Finance-Purchase-Order-icon72.png")));

		btnStores = new JButton("Stores");
		btnStores.setFont(font);
		btnStores.setHorizontalTextPosition(JButton.CENTER);
		btnStores.setVerticalTextPosition(JButton.BOTTOM);
		btnStores.setIcon(new ImageIcon(getClass().getResource("/icon/store-icon72.png")));

		btnBrands = new JButton("Brands");
		btnBrands.setFont(font);
		btnBrands.setHorizontalTextPosition(JButton.CENTER);
		btnBrands.setVerticalTextPosition(JButton.BOTTOM);
		btnBrands.setIcon(new ImageIcon(getClass().getResource("/icon/brand-icon72.png")));

		btnCategories = new JButton("Categories");
		btnCategories.setFont(font);
		btnCategories.setHorizontalTextPosition(JButton.CENTER);
		btnCategories.setVerticalTextPosition(JButton.BOTTOM);
		btnCategories.setIcon(new ImageIcon(getClass().getResource("/icon/Bicycle-icon72.png")));

		btnSignOut = new JButton("SignOut");
		btnSignOut.setFont(font);
		btnSignOut.setIcon(new ImageIcon(getClass().getResource("/icon/Sign_out72.png")));
		btnSignOut.setHorizontalTextPosition(JButton.CENTER);
		btnSignOut.setVerticalTextPosition(JButton.BOTTOM);

		JToolBar toolBar = new JToolBar();
		toolBar.add(btnProducts);
		toolBar.add(btnStaffs);
		toolBar.add(btnCustomers);
		toolBar.add(btnOrders);
		toolBar.add(btnStores);
		toolBar.add(btnBrands);
		toolBar.add(btnCategories);
		toolBar.add(btnSignOut);

		// lay out
		setLayout(new BorderLayout());
		// Page_Start
		add(toolBar, BorderLayout.PAGE_START);
		// Center
		panelCenter = new JPanel();
		cardLayout = new CardLayout();
		panelCenter.setLayout(cardLayout);

		products = new ProductsTable(this);
		staffs = new StaffsTable(this);
		customers = new CustomersTable(this);
		stores = new StoresTable(this);
		orders = new OrdersTable(this);
		brands = new BrandsTable(this);
		categories = new CategoriesTable(this);

		panelCenter.add(products, "1");
		panelCenter.add(staffs, "2");
		panelCenter.add(customers, "3");
		panelCenter.add(orders, "4");
		panelCenter.add(stores, "5");
		panelCenter.add(brands, "6");
		panelCenter.add(categories, "7");

		add(panelCenter, BorderLayout.CENTER);

		cardLayout.show(panelCenter, "1");
		// add actionListener
		AdminFrameController adminFrameController = new AdminFrameController(this);
		btnProducts.addActionListener(adminFrameController);
		btnStaffs.addActionListener(adminFrameController);
		btnCustomers.addActionListener(adminFrameController);
		btnOrders.addActionListener(adminFrameController);
		btnStores.addActionListener(adminFrameController);
		btnBrands.addActionListener(adminFrameController);
		btnCategories.addActionListener(adminFrameController);
		btnSignOut.addActionListener(adminFrameController);
	}

	public JButton getBtnProducts() {
		return btnProducts;
	}

	public void setBtnProducts(JButton btnProducts) {
		this.btnProducts = btnProducts;
	}

	public JButton getBtnStaffs() {
		return btnStaffs;
	}

	public void setBtnStaffs(JButton btnStaffs) {
		this.btnStaffs = btnStaffs;
	}

	public JButton getBtnCustomers() {
		return btnCustomers;
	}

	public void setBtnCustomers(JButton btnCustomers) {
		this.btnCustomers = btnCustomers;
	}

	public JButton getBtnOrders() {
		return btnOrders;
	}

	public void setBtnOrders(JButton btnOrders) {
		this.btnOrders = btnOrders;
	}

	public JButton getBtnStores() {
		return btnStores;
	}

	public void setBtnStores(JButton btnStores) {
		this.btnStores = btnStores;
	}

	public JButton getBtnBrands() {
		return btnBrands;
	}

	public void setBtnBrands(JButton btnBrands) {
		this.btnBrands = btnBrands;
	}

	public JButton getBtnCategories() {
		return btnCategories;
	}

	public void setBtnCategories(JButton btnCategories) {
		this.btnCategories = btnCategories;
	}

	public JButton getBtnSignOut() {
		return btnSignOut;
	}

	public void setBtnSignOut(JButton btnSignOut) {
		this.btnSignOut = btnSignOut;
	}

	public ProductsTable getProducts() {
		return products;
	}

	public void setProducts(ProductsTable products) {
		this.products = products;
	}

	public StaffsTable getStaffs() {
		return staffs;
	}

	public void setStaffs(StaffsTable staffs) {
		this.staffs = staffs;
	}

	public CustomersTable getCustomers() {
		return customers;
	}

	public void setCustomers(CustomersTable customers) {
		this.customers = customers;
	}

	public StoresTable getStores() {
		return stores;
	}

	public void setStores(StoresTable stores) {
		this.stores = stores;
	}

	public OrdersTable getOrders() {
		return orders;
	}

	public void setOrders(OrdersTable orders) {
		this.orders = orders;
	}

	public BrandsTable getBrands() {
		return brands;
	}

	public void setBrands(BrandsTable brands) {
		this.brands = brands;
	}

	public CategoriesTable getCategories() {
		return categories;
	}

	public void setCategories(CategoriesTable categories) {
		this.categories = categories;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getPanelCenter() {
		return panelCenter;
	}

	public void setPanelCenter(JPanel panelCenter) {
		this.panelCenter = panelCenter;
	}

}
