package ormdatabase.model;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CompressionStack implements Cloneable {
    List<Shim> stack;

    public CompressionStack() {
        stack = new ArrayList<>(List.of(new Shim("0", "0", "0")));
    }

    public CompressionStack(List<Shim> stack) {
        this.stack = stack;
    }

    public CompressionStack(CompressionStack compressionStack) {
        this.stack = new ArrayList<>(compressionStack.stack);
    }

    public List<Shim> getStack() {
        return stack;
    }

    @Override
    public CompressionStack clone() {
        try {
            CompressionStack clone = (CompressionStack) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
//            clone.stack = new ArrayList<>(clone.stack);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
