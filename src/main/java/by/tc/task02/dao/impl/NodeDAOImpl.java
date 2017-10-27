package by.tc.task02.dao.impl;

import by.tc.task02.dao.FileRefractor;
import by.tc.task02.dao.NodeDAO;
import by.tc.task02.dao.OldTempFileDeleteException;
import by.tc.task02.dao.creators.ContentEntityCreator;
import by.tc.task02.dao.creators.NodeEntityCreator;
import by.tc.task02.entity.AbstractEntity;
import by.tc.task02.entity.ContentEntity;
import by.tc.task02.entity.NodeEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeDAOImpl implements NodeDAO {
    private Deque<String> tags;
    private Deque<NodeEntity> entityDeque;
    private Deque<ContentEntity> contentEntities;
    private int currentLevel;

    private static final String OPENING_TAG="^<\\w*(\\s\\w*=\"\\w+\")*>$";
    private static final String CLOSING_TAG="</\\w*\\s?\\w*>$";
    private static final String TAG_SYMBOLS="(</)|<|>";
    private static final String EMPTY_SPACE="";
    private static final String REGEX_SPACE="\\s";
    private static final String CLOSING_TAG_ATTRIBUTES="[/>]";
    private static final String SOURCE_FILE_NAME= "task02.xml";
    private static final String SPLITTER_SYMBOL="=";
    private static final String ATTRIBUTE_MARKING_SYMBOLS="[\">]";
    private static final int FIRST_WORD_INDEX=0;
    private static final int MIN_ARRAY_SIZE=1;
    private static final int EMPTY_ARRAYDEQUE_SIZE=0;
    private static final int ATTRIBUTE_NAME_INDEX=0;
    private static final int ATTRIBUTE_VALUE_INDEX=1;

    public NodeDAOImpl(){
        tags=new ArrayDeque<>();
        entityDeque=new ArrayDeque<>();
        contentEntities=new ArrayDeque<>();

    }

    public NodeEntity parseFile(){
        FileRefractor fileRefractor=new FileRefractor();
        ClassLoader classLoader=getClass().getClassLoader();
        File sourceFile=new File(classLoader.getResource(SOURCE_FILE_NAME).getFile());
        try {
            File fileForParse=fileRefractor.rebuildFile(sourceFile);
            try(BufferedReader bufferedReader=new BufferedReader(new FileReader(fileForParse))){

                Pattern closingTagTemplate=Pattern.compile(CLOSING_TAG);
                Matcher closingTagMatcher;

                String currentString;
                while((currentString=bufferedReader.readLine())!=null){

                    closingTagMatcher=closingTagTemplate.matcher(currentString);

                    if(closingTagMatcher.matches()){
                        removeTag(currentString);
                        currentLevel--;
                    }else {
                        insertTag(currentString);
                    }
                }

                return entityDeque.pop();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (OldTempFileDeleteException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void removeTag(String currentString){
        String value;
        String[] tagStructure;
        boolean isContent=false;
        AbstractEntity contentEntity=null;

        value=tags.peek();
        tagStructure=tags.pop().split(REGEX_SPACE);
        String closingTagChecker=tagStructure[FIRST_WORD_INDEX];

        while(!closingTagChecker.contains(currentString.replaceAll(CLOSING_TAG_ATTRIBUTES,EMPTY_SPACE))){

            ContentEntityCreator contentCreator=new ContentEntityCreator();
            contentEntity = contentCreator.generate(currentLevel,currentString.replaceAll(TAG_SYMBOLS,EMPTY_SPACE),value);
            isContent=true;
            tagStructure=tags.pop().split(REGEX_SPACE);

            closingTagChecker=tagStructure[FIRST_WORD_INDEX];
        }
        if(isContent){
            fillEntityAttributes(tagStructure,contentEntity);
            contentEntities.push((ContentEntity) contentEntity);
        }else {
            addNode(tagStructure,currentString);
        }
    }

    private void fillEntityAttributes(String[] tagStructure,AbstractEntity abstractEntity){
        if(tagStructure.length>MIN_ARRAY_SIZE) {
            for (int i = MIN_ARRAY_SIZE; i < tagStructure.length; i++) {

                String[] correctPair=tagStructure[i].split(SPLITTER_SYMBOL);
                correctPair[ATTRIBUTE_VALUE_INDEX]=correctPair[ATTRIBUTE_VALUE_INDEX].replaceAll(ATTRIBUTE_MARKING_SYMBOLS,EMPTY_SPACE);
                abstractEntity.addAttribute(correctPair[ATTRIBUTE_NAME_INDEX],correctPair[ATTRIBUTE_VALUE_INDEX]);

            }
        }
    }
    private void fillNodeEntity(NodeEntity nodeEntity){
        while (contentEntities.size() != EMPTY_ARRAYDEQUE_SIZE && currentLevel<contentEntities.peek().getLevel()) {
            nodeEntity.addContent(contentEntities.pop());
        }

        while (entityDeque.size() != EMPTY_ARRAYDEQUE_SIZE && currentLevel < entityDeque.peek().getLevel()) {
            nodeEntity.addNode(entityDeque.pop());
        }
    }
    private void addNode(String[] tagStructure, String currentString){
        AbstractEntity nodeEntity;
        NodeEntityCreator nodeCreator=new NodeEntityCreator();
        nodeEntity= nodeCreator.generate(currentLevel,currentString.replaceAll(TAG_SYMBOLS,EMPTY_SPACE));

        fillEntityAttributes(tagStructure,nodeEntity);
        fillNodeEntity((NodeEntity) nodeEntity);

        entityDeque.push((NodeEntity) nodeEntity);
    }
    private void insertTag(String currentString){

        Pattern simpleTagTemplate=Pattern.compile(OPENING_TAG);
        Matcher simpleTagMatcher;
        simpleTagMatcher=simpleTagTemplate.matcher(currentString);

        if(simpleTagMatcher.matches()){
            currentLevel++;
        }
        tags.push(currentString);
    }

}
