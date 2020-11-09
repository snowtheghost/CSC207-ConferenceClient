public class SpeakerPanel implements IController {
    @Override
    public int run() {
        System.out.println("This is the speaker panel.");
        return Definitions.QUIT_APP;
    }
}
