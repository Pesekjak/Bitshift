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

public class ExprBitwise extends SimpleExpression<Number> {

    private Expression<Number> first;
    private Expression<Number> second;

    private int pattern;

    static {
        Skript.registerExpression(
                ExprBitwise.class, Number.class, ExpressionType.COMBINED,
                "%number% \\& %number%",
                "%number% \\| %number%",
                "%number% \\^\\^ %number%",
                "\\~%number%"
        );
    }

    @Override
    protected Number @NotNull [] get(@NotNull Event event) {
        Number first = this.first.getSingle(event);
        Number second = this.second != null ? this.second.getSingle(event) : null;
        if(first == null) first = 0;
        if(second == null) second = 0;
        return switch (pattern) {
            case 0 -> new Number[]{first.intValue() & second.intValue()};
            case 1 -> new Number[]{first.intValue() | second.intValue()};
            case 2 -> new Number[]{first.intValue() ^ second.intValue()};
            case 3 -> new Number[]{~first.intValue()};
            default -> new Number[0];
        };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean b) {
        return switch (pattern) {
            case 0 -> "Bitwise AND";
            case 1 -> "Bitwise inclusive OR";
            case 2 -> "Bitwise exclusive OR";
            case 3 -> "Unary bitwise complement";
            default -> "bitwise operator";
        };
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = i;
        first = LiteralUtils.defendExpression(expressions[0]);
        if(pattern != 3)
            second = LiteralUtils.defendExpression(expressions[1]);
        return true;
    }

}
