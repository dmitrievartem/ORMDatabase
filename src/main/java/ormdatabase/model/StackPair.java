package ormdatabase.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class StackPair implements Cloneable {
    @Embedded
    ReboundStack reboundStack;
    @Embedded
    CompressionStack compressionStack;

    public StackPair() {
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

    public CompressionStack getCompressionStack() {
        return compressionStack;
    }

    @Override
    public StackPair clone() {
        try {
            StackPair clone = (StackPair) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
