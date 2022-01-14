package ormdatabase.entity;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CompressionStack {
    List<Shim> stack;

    public CompressionStack() {
        stack = new ArrayList<>(List.of(new Shim("1", "1", "0")));
    }

    public CompressionStack(List<Shim> stack) {
        this.stack = stack;
    }

    public CompressionStack(CompressionStack compressionStack) {
        this.stack = new ArrayList<>();
        for (Shim shim : compressionStack.stack) {
            this.stack.add(new Shim(shim));
        }
    }

    public List<Shim> getStack() {
        return stack;
    }

}
