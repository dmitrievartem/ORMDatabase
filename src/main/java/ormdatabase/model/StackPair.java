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

    public StackPair(ReboundStack reboundStack, CompressionStack compressionStack) {
        this.compressionStack = compressionStack;
        this.reboundStack = reboundStack;
    }

    public CompressionStack getCompressionStack() {
        return compressionStack;
    }

    public ReboundStack getReboundStack() {
        return reboundStack;
    }

}
