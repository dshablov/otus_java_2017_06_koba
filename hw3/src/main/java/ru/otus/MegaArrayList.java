package ru.otus;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by Ricoshet on 17.06.2017.
 * <p>
 * Наивная имплементация ArrayList. Потоконебезопасная.
 */
public class MegaArrayList<T> implements List<T> {

    private final int INIT_SIZE = 10;
    private Object[] data;
    private int indexOfLastElement;


    public MegaArrayList() {
        data = new Object[INIT_SIZE];
        indexOfLastElement = -1;
    }


    @Override
    public void sort(Comparator<? super T> comparator) {
        Arrays.sort((T[]) data, 0, indexOfLastElement + 1, comparator);
    }

    @Override
    public int size() {
        return indexOfLastElement + 1;
    }

    @Override
    public boolean isEmpty() {
        return indexOfLastElement == -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MegaIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MegaIterator();
    }

    @Override
    public Object[] toArray() {
        return data;

    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MegaIterator(index);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) Arrays.copyOf(data, indexOfLastElement + 1, a.getClass());
    }

    @Override
    public void clear() {
        data = new Object[INIT_SIZE];
        indexOfLastElement = -1;
    }

    @Override
    public boolean add(T objectForAdd) {

        if (indexOfLastElement + 1 == data.length) {
            Object[] newData = new Object[data.length * 2 + 1];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        indexOfLastElement++;
        data[indexOfLastElement] = objectForAdd;
        return true;
    }

    @Override
    public T remove(int index) {
        T elementForRemove = (T) data[index];
        if (isLastElementInArray(index)) {
            data[index] = null;
        } else {
            Object[] newData = new Object[data.length];
            if (isFirstElementInArray(index)) {
                System.arraycopy(data, 1, newData, 0, indexOfLastElement);
            } else {
                System.arraycopy(data, 0, newData, 0, index);
                System.arraycopy(data, index + 1, newData, index, indexOfLastElement + 1 - index);
            }
            data = newData;
        }
        indexOfLastElement--;
        return elementForRemove;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i <= indexOfLastElement; i++) {
            if (data[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object obj) {
        for (Object element : data) {
            if (Objects.equals(obj, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        data[index] = element;
        return element;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T obj : collection) {
            add(obj);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collectionForCopy) {
        if (index >= indexOfLastElement + 1) {
            addAll(collectionForCopy);
            return true;
        }
        Object[] newData = new Object[data.length];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(collectionForCopy.toArray(), 0, newData, index, collectionForCopy.size());
        System.arraycopy(data, index, newData, index + collectionForCopy.size(), indexOfLastElement + 1 - index);
        data = newData;
        indexOfLastElement += collectionForCopy.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> objectsForDelete) {
        boolean hasDeletedElements = false;
        for (Object o : objectsForDelete) {
            if (remove(o)) {
                hasDeletedElements = true;
            }
        }
        return hasDeletedElements;
    }

    @Override
    public boolean retainAll(Collection<?> objectsForRetain) {
        List<? super Object> forDelete = new ArrayList<>();
        for (Object element : data) {
            if (!objectsForRetain.contains(element)) {
                forDelete.add(element);
            }
        }
        removeAll(forDelete);
        return !forDelete.isEmpty();
    }


    /**
     * Cначада был реализован метод addAll(int index, Collection<? extends T> collectionForCopy),
     * Для экономии времени и чтобы не дублировать код сделал такую имплементацию
     */
    @Override
    public void add(int index, T element) {
        addAll(index, asList(element));
    }

    @Override
    public int indexOf(Object o) {
        return index(o, SearchType.LEFT_TO_RIGHT);
    }


    @Override
    public int lastIndexOf(Object o) {
        return index(o, SearchType.RIGHT_TO_LEFT);
    }

    @Override
    public List<T> subList(int fromIndexInclusive, int toIndexExclusive) {
        List<T> result = new MegaArrayList<>();
        for (int i = fromIndexInclusive; i < toIndexExclusive; i++) {
            result.add((T) data[i]);
        }
        return result;
    }

    private class MegaIterator implements ListIterator<T> {

        private int currentPosition;

        public MegaIterator() {
            currentPosition = 0;
        }

        public MegaIterator(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        @Override
        public boolean hasNext() {
            return currentPosition <= indexOfLastElement;
        }


        @Override
        public T next() {
            return (T) data[currentPosition++];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }


        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(T t) {
            data[currentPosition - 1] = t;

        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();

        }

    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    private boolean isLastElementInArray(int index) {
        return index == indexOfLastElement;
    }

    private boolean isFirstElementInArray(int index) {
        return index == 0;
    }

    private int index(Object o, SearchType searchType) {
        int indexOfElement = -1;
        for (int i = 0; i <= indexOfLastElement; i++) {
            if (Objects.equals(data[i], o)) {
                indexOfElement = i;
                if (searchType == SearchType.LEFT_TO_RIGHT) {
                    break;
                }
            }
        }
        return indexOfElement;
    }

    private enum SearchType {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }


}
