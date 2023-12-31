package com.allways.common.helper;

import com.allways.common.exception.CannotConvertNestedStructureException;

import java.util.*;
import java.util.function.Function;

public class NestedConvertHelper<K, E, D> {

    private List<E> entities;
    private Function<E, D> toDto;
    private Function<E, E> getParent;
    private Function<E, K> getKey;
    private Function<D, List<D>> getChildren;

    public static <K, E, D> NestedConvertHelper newInstance(List<E> entities, Function<E, D> toDto, Function<E, E> getParent, Function<E, K> getKey, Function<D, List<D>> getChildren) {
        return new NestedConvertHelper<K, E, D>(entities, toDto, getParent, getKey, getChildren);
    }

    public static <K, E, D> NestedConvertHelper newInstance(List<E> entities, Function<E, D> toDto, Function<E, K> getKey, Function<D, List<D>> getChildren) {
        return new NestedConvertHelper<K, E, D>(entities, toDto, getKey, getChildren);
    }

    private NestedConvertHelper(List<E> entities, Function<E, D> toDto, Function<E, K> getKey, Function<D, List<D>> getChildren) {
        this.entities = entities;
        this.toDto = toDto;
        this.getKey = getKey;
        this.getChildren = getChildren;
    }

    private NestedConvertHelper(List<E> entities, Function<E, D> toDto, Function<E, E> getParent, Function<E, K> getKey, Function<D, List<D>> getChildren) {
        this.entities = entities;
        this.toDto = toDto;
        this.getParent = getParent;
        this.getKey = getKey;
        this.getChildren = getChildren;
    }

    public List<D> convert() {
        try {
            return convertInternal();
        } catch (NullPointerException e) {
            throw new CannotConvertNestedStructureException(e.getMessage());
        }
    }

    public List<D> convert2(){
        try {
            return convertInternal2();
        } catch (NullPointerException e){
            throw new CannotConvertNestedStructureException(e.getMessage());
        }
    }

    private List<D> convertInternal() {
        Map<K, D> map = new HashMap<>();
        List<D> roots = new ArrayList<>();

        for (E e : entities) {
            D dto = toDto(e);
            map.put(getKey(e), dto);
            if (hasParent(e)) {
                E parent = getParent(e);
                K parentKey = getKey(parent);
                D parentDto = map.get(parentKey);
                getChildren(parentDto).add(dto);
            } else {
                roots.add(dto);
            }
        }
        return roots;
    }

    private List<D> convertInternal2() {
        Map<K, D> map2 = new HashMap<>();
        List<D> roots2 = new ArrayList<>();

        for (E e : entities) {
            D dto = toDto(e);
            map2.put(getKey(e), dto);
//            if (hasParent(e)) {
//                E parent = getParent(e);
//                K parentKey = getKey(parent);
//                D parentDto = map.get(parentKey);
//                getChildren(parentDto).add(dto);
//            } else {
//                roots.add(dto);
//            }
        }
        return roots2;
    }

    private boolean hasParent(E e) {
        return getParent(e) != null;
    }

    private E getParent(E e) {
        return getParent.apply(e);
    }

    private D toDto(E e) {
        return toDto.apply(e);
    }

    private K getKey(E e) {
        return getKey.apply(e);
    }

    private List<D> getChildren(D d) {
        return getChildren.apply(d);
    }
}