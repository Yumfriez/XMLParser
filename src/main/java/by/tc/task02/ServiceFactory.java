package by.tc.task02;

import by.tc.task02.service.NodeServiceImpl;

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
