package by.tc.task02.dao.creators;

import by.tc.task02.entity.AbstractEntity;
import by.tc.task02.entity.ContentEntity;

public class ContentEntityCreator implements Generative {
    private final static int NAME_INDEX=0;
    private final static int VALUE_INDEX=1;
    @Override
    public AbstractEntity generate(int level,String...insights) {
        ContentEntity contentEntity=new ContentEntity();
        contentEntity.setName(insights[NAME_INDEX]);
        contentEntity.setValue(insights[VALUE_INDEX]);
        contentEntity.setLevel(level);
        return contentEntity;
    }
}
