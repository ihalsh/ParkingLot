import org.hyperskill.hstest.v6.stage.BaseStageTest;
import parking.MainKt;

public abstract class ParkingTest<T> extends BaseStageTest<T> {
    public ParkingTest() {
        super(MainKt.class);
    }
}
