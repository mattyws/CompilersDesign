/* This file was generated by SableCC (http://www.sablecc.org/). */

package teste.analysis;

import teste.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseTFor(TFor node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
