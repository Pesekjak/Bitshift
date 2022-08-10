package me.pesekjak.bitshift.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class ExprReverseBoolean extends SimpleExpression<Boolean> {

    private Expression<Boolean> booleanExpression;

    static {
        Skript.registerExpression(
                ExprReverseBoolean.class,
                Boolean.class,
                ExpressionType.COMBINED,
                "\\!%boolean%"
        );
    }

    @Override
    protected Boolean @NotNull [] get(@NotNull Event event) {
        if(booleanExpression == null) return new Boolean[0];
        Boolean input = booleanExpression.getSingle(event);
        if(input == null) return new Boolean[0];
        return new Boolean[]{!input};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean b) {
        return "Logical complement";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        booleanExpression = LiteralUtils.defendExpression(expressions[0]);
        return true;
    }
}
