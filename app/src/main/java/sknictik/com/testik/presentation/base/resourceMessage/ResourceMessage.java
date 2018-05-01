package sknictik.com.testik.presentation.base.resourceMessage;

import java.io.Serializable;

/**
 * Data class containing message information.
 */
public class ResourceMessage implements Serializable {

    private Object mBaseMessage;
    private Object[] mInlineVars;

    public ResourceMessage() {
        mBaseMessage = "";
    }

    public ResourceMessage(Object baseMessage) {
        this(baseMessage, null);
    }

    public ResourceMessage(Object baseMessage, Object... inlineVars) {
        mBaseMessage = baseMessage;
        mInlineVars = inlineVars;
    }

    public Object getBaseMessage() {
        return mBaseMessage;
    }

    public Object[] getInlineVars() {
        return mInlineVars;
    }
}
