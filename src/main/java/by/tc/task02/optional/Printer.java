package by.tc.task02.optional;

import by.tc.task02.entity.ContentEntity;
import by.tc.task02.entity.NodeEntity;

import java.util.List;

public class Printer {
    private static final String NODE_MARKER="NODE_NAME: ";
    private static final String LEVEL_MARKER=", LEVEL: ";
    private static final String ATTRIBUTES_MARKER=", ATTRIBUTES: ";
    private static final String CONTENT_MARKER=" CONTENT {";
    private static final String CHILD_NODE_MARKER=" CHILD_NODE = ";
    private static final String NAME_AND_VALUE_SPLITTER=": ";
    private static final String END_OF_CONTENT_MARKER="}";
    private static final String SPACER_MULTIPLIER="      ";
    public void print(String spaces,NodeEntity nodeEntity){
        if(nodeEntity==null){
            return;
        }
        System.out.print(spaces+NODE_MARKER+nodeEntity.getName());
        System.out.print(LEVEL_MARKER+nodeEntity.getLevel());
        if(!nodeEntity.getAttributes().isEmpty()) {
            System.out.println(ATTRIBUTES_MARKER + nodeEntity.getAttributes());
        }
        else{
            System.out.println();
        }
        List<ContentEntity> contentEntities=nodeEntity.getContentEntities();
        for (ContentEntity contentEntity : contentEntities) {
            System.out.print(spaces +CONTENT_MARKER + contentEntity.getName()+NAME_AND_VALUE_SPLITTER+contentEntity.getValue());
            System.out.print(END_OF_CONTENT_MARKER);
            if(!contentEntity.getAttributes().isEmpty()){
                System.out.println(ATTRIBUTES_MARKER+contentEntity.getAttributes());
            }else{
                System.out.println();
            }
        }
        List<NodeEntity> nodeEntities=nodeEntity.getNodeEntities();
        for (NodeEntity innerNodeEntity : nodeEntities) {
            System.out.println(spaces + CHILD_NODE_MARKER + innerNodeEntity.getName());
        }
        System.out.println();
        for (NodeEntity innerEntity : nodeEntities) {
           this.print(spaces+SPACER_MULTIPLIER,innerEntity);
        }
    }


}

