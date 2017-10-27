package by.tc.task02.main;

import by.tc.task02.NodeService;
import by.tc.task02.ServiceFactory;
import by.tc.task02.entity.NodeEntity;
import by.tc.task02.optional.Printer;

public class Launcher {
    private static final String INITIAL_SPACER=" ";
    public static void main(String[] args) {

       NodeEntity mainNode;

        ServiceFactory factory = ServiceFactory.getInstance();
        NodeService service = factory.getNodeService();


        mainNode = service.parseFile();


        Printer printer=new Printer();

        printer.print(INITIAL_SPACER,mainNode);
    }
}
