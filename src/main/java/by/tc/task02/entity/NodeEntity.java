package by.tc.task02.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class NodeEntity extends AbstractEntity implements Serializable{
    private List<NodeEntity> nodeEntities;
    private List<ContentEntity> contentEntities;
    public NodeEntity(){
        nodeEntities=new LinkedList<>();
        contentEntities=new LinkedList<>();
    }

    public List<NodeEntity> getNodeEntities() {

        return nodeEntities;
    }

    public void setNodeEntities(List<NodeEntity> nodeEntities) {
        this.nodeEntities = nodeEntities;
    }

    public List<ContentEntity> getContentEntities() {
        return contentEntities;
    }

    public void setContentEntities(List<ContentEntity> contentEntities) {
        this.contentEntities = contentEntities;
    }

    public void addContent(ContentEntity contentEntity){
        contentEntities.add(0,contentEntity);
    }

    public void addNode(NodeEntity nodeEntity){
        nodeEntities.add(0,nodeEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NodeEntity that = (NodeEntity) o;

        if (!nodeEntities.equals(that.nodeEntities)) return false;
        return contentEntities.equals(that.contentEntities);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nodeEntities.hashCode();
        result = 31 * result + contentEntities.hashCode();
        return result;
    }

}
