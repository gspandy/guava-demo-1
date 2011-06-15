package demo.guava;

import java.util.Collection;
import java.util.Date;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 */
public class Planet {
    private final String name;
    private final int mass;
    private boolean isInhabitable;
    private Date dateDiscovered;
    private final Collection<PlanetAttribute> attributes = Lists.newArrayList();

    public Collection<PlanetAttribute> getAttributes() {
        return attributes;
    }

    public boolean isInhabitable() {
        return isInhabitable;
    }

    public void setInhabitable(final boolean isInhabitable) {
        this.isInhabitable = isInhabitable;
    }

    public Date getDateDiscovered() {
        return dateDiscovered;
    }

    public void setDateDiscovered(final Date dateDiscovered) {
        this.dateDiscovered = dateDiscovered;
    }

    /**
     * @param nameValue
     * @param massValue
     */
    public Planet(final String nameValue, final int massValue) {
        this.name = Preconditions.checkNotNull(nameValue);
        Preconditions.checkArgument(massValue > 0);
        this.mass = massValue;
    }

    public PlanetAttribute addAttribute(final String key, final Object value) {
        PlanetAttribute att = new PlanetAttribute(key, value);
        attributes.add(att);
        return att;
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
