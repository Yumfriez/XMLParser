package by.tc.task02.dao.creators;

import by.tc.task02.entity.AbstractEntity;
import by.tc.task02.entity.NodeEntity;

public class NodeEntityCreator implements Generative {
private final static int NAME_INDEX=0;
    @Override
    public AbstractEntity generate(int level,String...insights) {
        NodeEntity nodeEntity=new NodeEntity();
        nodeEntity.setName(insights[NAME_INDEX]);
        nodeEntity.setLevel(level);
        return nodeEntity;
    }
}
