package fr.cnam.tp9;

public class Heditor extends Editor implements HisEditor {
    private CommandHistory undoHistory;
    private CommandHistory redoHistory;

    private UndoCommand undoCommand=new UndoCommand(this);
    private RedoCommand redoCommand=new RedoCommand(this);

    public Heditor(Line a_Line){
        super(a_Line);
        this.undoHistory =new LineCommandHistory();
        this.redoHistory =new LineCommandHistory();
        this.currentMenu.add(new Entry("Undo",undoCommand,"-"));
        this.currentMenu.add(new Entry("Redo",redoCommand,"+"));

    }

    @Override
    public CommandHistory getUndoHistory() {
        return this.undoHistory;
    }

    @Override
    public CommandHistory getRedoHistory() {
        return this.redoHistory;
    }

    @Override
    public void redo() {

        Line previousLine=this.redoHistory.pull();
        this.undoHistory.push(this.line.clone());
        this.restoreLine(previousLine);




    }

    @Override
    public void undo() {
        Line previousLine=this.undoHistory.pull();
        this.redoHistory.push(this.line.clone());
        this.restoreLine(previousLine);




    }


    @Override
    protected void execute(Command a_Command) {
        if(a_Command.isCancellable()){
                this.undoHistory.push (this.line.clone());

        }
        super.execute(a_Command);
    }

    protected void restoreLine(Line a_Line){
        this.line=a_Line;

        for(int i=0;i<this.nbLineCommands;i++) this.lineCommandsTable[i].setLine(a_Line);
    }
}
