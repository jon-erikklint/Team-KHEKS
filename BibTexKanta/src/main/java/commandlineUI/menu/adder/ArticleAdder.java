package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.Command;
import commandlineUI.JournalCommand;
import commandlineUI.MonthCommand;
import commandlineUI.NoteCommand;
import commandlineUI.NumberCommand;
import commandlineUI.PagesCommand;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.SaveToDbCommand;
import commandlineUI.VolumeCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.Reference;
import service.BibTexIdService;

public class ArticleAdder extends AbstractAdder<ArticleRef> {

    public ArticleAdder(DAO<ArticleRef> dao, IO io, BibTexIdService service) {

        super(dao, io, new String[]{},
                new String[]{
                    "Artikkeliviitteen lisääminen:\n",
                    "1 Artikkelin nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Vuosikerta",
                    "8 Numero",
                    "9 Lehti",
                    "10 Sivut",
                    "11 Tallenna ja lopeta",
                    "12 Näytä syötetyt tiedot",
                    "13 Lopeta tallentamatta",
                    "14 Lisää viitteeseen tägejä",
                    "15 Lisää kuukausi",
                    "16 Lisätietoja"
                }
        );

        Map<String, Command> commands = super.getCommands();
        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io, dao, service));
        commands.put("7", new VolumeCommand(io));
        commands.put("8", new NumberCommand(io));
        commands.put("9", new JournalCommand(io));
        commands.put("10", new PagesCommand(io));
        commands.put("11", new SaveToDbCommand(io, dao, service));
        commands.put("12", new PrintStatusCommand(io));
        commands.put("13", new QuitCommand());
        commands.put("14", new TagEditor(io));
        commands.put("15", new MonthCommand(io));
        commands.put("16", new NoteCommand(io));
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return new ArticleRef();
    }
}
