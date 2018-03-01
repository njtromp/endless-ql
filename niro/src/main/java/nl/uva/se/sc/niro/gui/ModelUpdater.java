package nl.uva.se.sc.niro.gui;

import nl.uva.se.sc.niro.model.Expressions.Answer;

/**
 * Classes that implement the @{@link ModelUpdater} interface are responsible for updating the answer for a single
 * question.
 */
public interface ModelUpdater {
    /**
     * Update the answer for a single question based on the questionId.
     * @param questionId the id of the question to update.
     * @param answer the new value for the question.
     */
    void updateModel(String questionId, Answer answer);
}