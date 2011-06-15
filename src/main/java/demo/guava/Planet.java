package demo.guava;

import com.google.common.base.Preconditions;

/**
 */
public class Planet {
    private final String name;
    private final int mass;

    /**
     * @param nameValue
     * @param massValue
     */
    public Planet(final String nameValue, final int massValue) {
        this.name = Preconditions.checkNotNull(nameValue);
        Preconditions.checkArgument(massValue > 0);
        this.mass = massValue;
    }

    /**
     * @return the planet's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the planet's mass
     */
    public int getMass() {
        return this.mass;
    }
}
