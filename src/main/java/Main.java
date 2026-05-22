import userInteractions.UserInteractions;

/***
 * README :)
 * What changed?
 *  - Products were looked at as data points with a consistent structure.
 *      This allowed the menu to be easily updated by adding new objects instead of classes.
 *
 *  - Composition was preferred to inheritance.
 *      This setup did not require any inheritance.
 *
 *  - Program executes without looping.
 *      This was done to keep the focus on exploring the new setup.
 *
 *  - Stream API was used to simplify the filtering.
 *
 *  TL;DR
 *  See documentation directory in project root.
 *
 *  Interested in fixing something, looking forward to your PR
 */

public class Main {
    public static void main() {
        UserInteractions.run();
    }
}
