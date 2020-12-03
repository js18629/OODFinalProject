import com.uab.controller.TextController;
import com.uab.model.Composition;
import com.uab.model.Glyph;
import com.uab.model.decorator.BorderDecorator;
import com.uab.model.decorator.ScrollDecorator;
import com.uab.model.strategies.Compositor;
import com.uab.model.strategies.SimpleCompositor;

public class App {
	public static void main(String[] args) {
		Compositor strategy = new SimpleCompositor();
		Glyph glyph = new Composition(strategy);


		TextController NotepadMinusMinus = new TextController(glyph);

	}
}
