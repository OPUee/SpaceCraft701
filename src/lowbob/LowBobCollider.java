package lowbob;

import app.impl.sprites.SC_S_Missile;

/**
 * Created by opuee on 27.04.17.
 */
public class LowBobCollider {

    private Class type;

    public LowBobCollider(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

}
