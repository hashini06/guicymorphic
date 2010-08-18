package guicymorphic.security;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 17:28:16
 * To change this template use File | Settings | File Templates.
 */
public class AclQuery {

    public final LinkedList<AclQueryEntry> elements;
    public final AclElement selectedElement;

    public AclQuery(LinkedList<AclQueryEntry> elements, AclElement selectedElement) {
        this.elements = elements;
        this.selectedElement = selectedElement;
    }

    public static class Builder {

        private LinkedList<AclQueryEntry> elements = new LinkedList<AclQueryEntry>();

        private AclElement selectedElement;

        public Builder(AclQueryEntry aclQueryEntry) {
            elements.add(aclQueryEntry);
        }

        public Builder filter(AclElement element, String principalType, String principalId) {
            elements.add(new AclQueryEntry(element, principalType, principalId));
            return this;
        }

        public AclQuery select(AclElement element) {
            selectedElement = element;
            return new AclQuery(elements, selectedElement);
        }


    }

    public static class AclQueryEntry {
        public final AclElement element;
        public final String elementId;
        public final String elementType;

        public AclQueryEntry(AclElement element, String elementType, String elementId) {
            this.element = element;
            this.elementType = elementType;
            this.elementId = elementId;
        }


    }

    public static Builder filter(AclElement element, String elementType, String elementId) {
        return new Builder(new AclQueryEntry(element, elementType, elementId));
    }


}
