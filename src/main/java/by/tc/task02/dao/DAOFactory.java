package by.tc.task02.dao;

import by.tc.task02.dao.impl.NodeDAOImpl;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final NodeDAO nodeDAO = new NodeDAOImpl();
	
	private DAOFactory() {}

	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
