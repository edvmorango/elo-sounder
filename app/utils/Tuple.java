package utils;

public class Tuple<A,B> {

    public final A a;
    public final B b;

    public Tuple(A a, B b){
        this.a = a;
        this.b = b;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj != null && obj.getClass() != this.getClass())
            return false;

        Tuple<?,?> tup = (Tuple<?,?>) obj;
        return tup.a.equals(this.a) && tup.b.equals(this.b);
    }
}
