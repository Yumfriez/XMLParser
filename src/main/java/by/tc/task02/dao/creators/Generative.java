package by.tc.task02.dao.creators;

import by.tc.task02.entity.AbstractEntity;

public interface Generative {
    AbstractEntity generate(int level,String...insights);
}
