package me.pesekjak.bitshift.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class CondCustom extends Condition {

    private Expression<Boolean> booleanExpression;
    private Condition condition;

    private int pattern;

    static {
        Skript.registerCondition(
                CondCustom.class,
                "\\!<.+>",
                "%boolean%\\?"
        );
    }

    @Override
    public boolean check(@NotNull Event event) {
        if(pattern == 0) {
            if(condition == null) return false;
            return !condition.check(event);
        } else {
            Boolean input = booleanExpression.getSingle(event);
            if(input == null) return false;
            return input;
        }
    }

    @Override
    public @NotNull String toString(Event event, boolean b) {
        return pattern == 0 ? "Reversed Condition" : "Is True?";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = i;
        if(pattern == 0) {
            String regex = parseResult.regexes.get(0).group();
            condition = Condition.parse(regex, "Can't understand this condition: '" + regex + "'");
            return condition != null;
        } else
            booleanExpression = LiteralUtils.defendExpression(expressions[0]);
        return true;
    }

}
