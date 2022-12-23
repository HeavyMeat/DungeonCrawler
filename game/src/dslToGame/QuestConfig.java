package dslToGame;

import graph.Graph;
import semanticAnalysis.types.DSLType;
import semanticAnalysis.types.DSLTypeMember;

// TODO: add more fields (entry-point for interpreter, QuestType, etc.)
@DSLType
public record QuestConfig(
        @DSLTypeMember Graph<String> levelGraph,
        @DSLTypeMember String questDesc,
        @DSLTypeMember int questPoints,
        @DSLTypeMember String password) {}
