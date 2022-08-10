package me.pesekjak.bitshift.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class CondConditional extends Condition {

    private Condition first;
    private Condition second;

    private int pattern;

    static {
        Skript.registerCondition(
                CondConditional.class,
                "<.+> \\&\\& <.+>",
                "<.+> \\|\\| <.+>"
        );
    }

    @Override
    public boolean check(@NotNull Event event) {
        if(pattern == 0)
            return first.check(event) && second.check(event);
        else
            return first.check(event) || second.check(event);
    }

    @Override
    public @NotNull String toString(Event event, boolean b) {
        return pattern == 0 ? "Conditional AND" : "Conditional OR";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = i;
        String firstText = parseResult.regexes.get(0).group(0);
        String secondText = parseResult.regexes.get(1).group(0);
        first = Condition.parse(firstText, "Can't understand this condition: '" + firstText + "'");
        second = Condition.parse(secondText, "Can't understand this condition: '" + secondText + "'");
        return first != null && second != null;
    }

}
