package com.github.gabrielsilper.dwebservlets.model;

import java.util.Objects;

public class State {
    private String name;
    private String stateCode;

    public State() {
    }

    public State(String name, String stateCode) {
        this.name = name;
        this.stateCode = stateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name) && Objects.equals(stateCode, state.stateCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stateCode);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
