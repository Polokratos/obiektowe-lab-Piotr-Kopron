package agh.ics.oop;

public interface IMapElement {
    /**
     * Interface is not implemented (leaving a stub so this comment is easy to find.)
     * In favour : Some code is duplicated an can be cleared.
     * Against: base class would do it better and better represent the relationship.
     * Against: GrassField still needs different stores of animals and grass.
     *      Explanation: since sometimes we only need to iterate over all animals (canMoveTo),
     *      and sometimes only over grass (check where to grow next grass),
     *      we need different caches to optimize unnescescesary checks.
     */
}
