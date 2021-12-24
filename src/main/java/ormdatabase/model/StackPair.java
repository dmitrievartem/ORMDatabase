package ormdatabase.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class StackPair {
    @Embedded
    CompressionStack compressionStack;
    @Embedded
    ReboundStack reboundStack;

    public StackPair() {
    }

    public StackPair(CompressionStack compressionStack, ReboundStack reboundStack) {
        this.compressionStack = compressionStack;
        this.reboundStack = reboundStack;
    }

    public CompressionStack getCompressionStack() {
        return compressionStack;
    }

    public void setCompressionStack(CompressionStack compressionStack) {
        this.compressionStack = compressionStack;
    }

    public ReboundStack getReboundStack() {
        return reboundStack;
    }

    public void setReboundStack(ReboundStack reboundStack) {
        this.reboundStack = reboundStack;
    }
}
