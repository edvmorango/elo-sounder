package utils;

public class Tuple<A,B> {

    public final A x;
    public final B y;

    public Tuple(A x, B y){
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj != null && obj.getClass() != this.getClass())
            return false;

        Tuple<?,?> tup = (Tuple<?,?>) obj;
        return tup.x.equals(this.x) && tup.y.equals(this.y);
    }





}
