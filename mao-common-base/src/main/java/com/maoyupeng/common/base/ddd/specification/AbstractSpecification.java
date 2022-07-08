package com.maoyupeng.common.base.ddd.specification;


import com.maoyupeng.common.base.exception.JarvisException;

/**
 * Abstract base implementation of composite {@link Specification} with default
 * implementations for {@code and}, {@code or} and {@code not}.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isSatisfiedBy(T t)  throws JarvisException;

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> and(final Specification<T> specification)  throws JarvisException {
        return new AndSpecification<>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> or(final Specification<T> specification)  throws JarvisException {
        return new OrSpecification<>(this, specification);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Specification<T> not(final Specification<T> specification)  throws JarvisException {
        return new NotSpecification<>(specification);
    }
}
