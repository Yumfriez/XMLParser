package by.tc.task02.dao;

import java.io.*;

public class FileRefractor {
    private static final String REDUNDANT_SPACES="\\s{2,}";
    private static final String SIMPLE_SPACE=" ";
    private static final String REDUNDANT_SPACES_BEFORE_OPENING_TAG="^\\s+";
    private static final String EMPTY_SPACE="";
    private static final String END_OF_TAG=">\\s*";
    private static final String REPLACEMENT_FOR_END_OF_TAG=">@";
    private static final String BEGIN_OF_CLOSING_TAG="\\s*</";
    private static final String REPLACEMENT_FOR_BEGIN_OF_CLOSING_TAG="@</";
    private static final String TEMP_FILE="temp.xml";
    private static final String SPLITTER_SYMBOL="@";
    private static final String DELETE_EXCEPTION_TEXT="Возникла ошибка при удаления старого временного файла";
    private static final int ZERO_SIZE=0;
    private static final String META_INFO="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final char END_OF_STRING='\n';

    public File rebuildFile(File fileForRebuild) throws OldTempFileDeleteException{

        String filePath=fileForRebuild.getAbsolutePath().replaceAll(fileForRebuild.getName(),TEMP_FILE);

        File tempFile=new File(filePath);

        if(tempFile.exists()){
            if(!tempFile.delete()){
                throw new OldTempFileDeleteException(DELETE_EXCEPTION_TEXT);
            }
        }

        try(BufferedReader br=new BufferedReader(new FileReader(fileForRebuild)); FileWriter fw=new FileWriter(tempFile,true)){
            String currentString;
            while((currentString=br.readLine())!=null){

                String[] correctStrings=rebuildString(currentString);

                for(String stringForWriting:correctStrings){
                    if(!stringForWriting.isEmpty()) {
                        fw.write(stringForWriting + END_OF_STRING);
                    }
                }

            }
            return tempFile;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] rebuildString(String currentString){
        if(currentString.equals(META_INFO)){
            return new String[ZERO_SIZE];
        }

        currentString=currentString.replaceAll(END_OF_TAG,REPLACEMENT_FOR_END_OF_TAG);

        currentString=currentString.replaceAll(BEGIN_OF_CLOSING_TAG,REPLACEMENT_FOR_BEGIN_OF_CLOSING_TAG);

        currentString=currentString.replaceAll(REDUNDANT_SPACES,SIMPLE_SPACE);

        currentString=currentString.replaceAll(REDUNDANT_SPACES_BEFORE_OPENING_TAG,EMPTY_SPACE);

        return currentString.split(SPLITTER_SYMBOL);
    }
}
