package utn.frc.bda.semana04.collections.simple;

public class SimpleLinkedList {

    private static class Node {
        Node prev;
        Object element;
        Node next;

        Node(Object element) {
            super();
            this.element = element;
        }

        Node(Node prev, Object element, Node next) {
            this(element);
            this.prev = prev;
            this.next = next;
        }

        Node(Node prev, Object element) {
            this(prev, element, null);
        }

        Node(Object element, Node next) {
            this(null, element, next);
        }

        Node delete() {
            Node r = null;

            if (prev != null) {
                prev.next = next;
                r = prev;
            }

            if (next != null) {
                next.prev = prev;
                r = next;
            }

            return r;
        }

        Node insert(Object element) {
            Node r = new Node(prev, element, this);

            if (prev != null) {
                prev.next = r;
            }

            prev = r;

            return r;
        }
    }

    private int cant, index;
    Node first, current, last;

    public SimpleLinkedList() {
        super();
        cant = 0;
        index = -1;
    }

    public int size() {
        return cant;
    }

    public boolean first() {
        current = first;

        if (current != null) {
            index = 0;
        }

        return (index == 0);
    }

    public boolean last() {
        current = last;

        if (current != null) {
            index = cant-1;
        }

        return (index == cant-1);
    }

    public boolean prior() {
        boolean r = false;

        if (current != null) {
            if (current.prev != null) {
                current = current.prev;
                index--;
                r = true;
            }
        }

        return r;
    }

    public boolean next() {
        boolean r = false;

        if (current != null) {
            if (current.next != null) {
                current = current.next;
                index++;
                r = true;
            }
        }

        return r;
    }

    private boolean back(int steps) {
        boolean r = false;

        int newIndex = index - steps;

        if (newIndex >= 0) {

            int fwdSteps = newIndex;

            if (fwdSteps < steps) {

                first();

                r = fwd(fwdSteps);

            } else {

                for (int i = 0; i < steps; i++) {
                    current = current.prev;
                }

                index = newIndex;

                r = true;
            }
        }

        return r;
    }

    private boolean fwd(int steps) {
        boolean r = false;

        int newIndex = index + steps;

        if (newIndex < cant) {

            int backSteps = cant - 1 - newIndex;

            if (backSteps < steps) {

                last();

                r = back(backSteps);

            } else {

                for (int i = 0; i < steps; i++) {
                    current = current.next;
                }

                index = newIndex;

                r = true;
            }
        }

        return r;
    }

    private boolean move(int steps) {
        return steps < 0 ? back(-steps) : fwd(steps);
    }

    private boolean goTo(int newIndex) {
        return move(newIndex-index);
    }

    public Object element(int index) {
        return goTo(index) ? current.element : null;
    }

    public Object element() {
        return current != null ? current.element : null;
    }

    public Object remove(int index) {
        Object r = element(index);

        if (r != null) {
            current = current.delete();
            cant--;
        }

        return r;
    }

    public Object remove() {
        Object r = element();

        if (r != null) {
            current = current.delete();
            cant--;
        }

        return r;
    }

    public int add(Object element) {
        if (element == null) return -1;

        Node node = new Node(last, element);

        if (cant == 0) {
            first = current = node;
            index = 0;
        } else {
            last.next = node;
            node.prev = last;
        }

        last = node;

        return (cant++);
    }

    public boolean insert(int index, Object element) {
        if (element == null) return false;

        boolean r = goTo(index) || index == 0;

        if (r) {
            if (current != null) {
                current = current.insert(element);
            } else {
                first = current = last = new Node(element);
            }
            cant++;
        }

        return r;
    }

    public static void main(String[] args) {
        SimpleLinkedList list = new SimpleLinkedList();
        list.add("Hola");
        list.add(48);
        if (list.first()) {
            do {
                System.out.println(list.element());
            } while (list.next());
        }
    }

}


