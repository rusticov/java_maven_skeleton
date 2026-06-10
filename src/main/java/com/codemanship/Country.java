package com.codemanship;

public enum Country {
    UNITED_KINGDOM(Region.LOCAL),
    FRANCE(Region.EU),
    GERMANY(Region.EU);

    public final Region region;

    Country(Region region) {
        this.region = region;
    }
}
