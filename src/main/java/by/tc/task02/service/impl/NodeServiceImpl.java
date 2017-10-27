package by.tc.task02.service.impl;


import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.NodeDAO;
import by.tc.task02.entity.NodeEntity;
import by.tc.task02.service.NodeService;

public class NodeServiceImpl implements NodeService {

	@Override
	public NodeEntity parseFile() {
		DAOFactory factory = DAOFactory.getInstance();
		NodeDAO nodeDAO = factory.getNodeDAO();
		
		NodeEntity nodeEntity = nodeDAO.parseFile();
		
		return nodeEntity;
	}

}

