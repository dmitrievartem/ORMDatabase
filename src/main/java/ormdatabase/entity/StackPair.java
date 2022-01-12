package ormdatabase.entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class StackPair {
    @Embedded
    ReboundStack reboundStack;
    @Embedded
    CompressionStack compressionStack;

    public StackPair() {
        reboundStack = new ReboundStack();
        compressionStack = new CompressionStack();
    }

    public StackPair(ReboundStack reboundStack, CompressionStack compressionStack) {
        this.reboundStack = reboundStack;
        this.compressionStack = compressionStack;
    }

    public StackPair(StackPair stackPair) {
        this.reboundStack = new ReboundStack(stackPair.reboundStack);
        this.compressionStack = new CompressionStack(stackPair.compressionStack);
    }

    public ReboundStack getReboundStack() {
        return reboundStack;
    }

    public void setReboundStack(ReboundStack reboundStack) {
        this.reboundStack = reboundStack;
    }

    public CompressionStack getCompressionStack() {
        return compressionStack;
    }

    public void setCompressionStack(CompressionStack compressionStack) {
        this.compressionStack = compressionStack;
    }
}
