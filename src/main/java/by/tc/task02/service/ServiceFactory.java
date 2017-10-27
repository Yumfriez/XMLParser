package by.tc.task02.service;

import by.tc.task02.service.impl.NodeServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final NodeService nodeService = new NodeServiceImpl();
	
	private ServiceFactory() {}

	public NodeService getNodeService() {

		return nodeService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}
