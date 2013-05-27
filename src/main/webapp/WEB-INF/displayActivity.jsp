                            <c:if test="${activity.production > 0}">               
                            <td class="production" colspan="${activity.productionColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.production}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.terrain > 0}">
                            <td class="terrain" colspan="${activity.terrainColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.terrain}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.copil > 0}">
                            <td class="copil" colspan="${activity.copilColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.copil}
                                </a>
                            </td>
                            </c:if><c:if test="${activity.conges > 0}">
                            <td class="conges" colspan="${activity.congesColspan}">
                                <a href="editActivity?activity-id=${activity.id}&amp;project-id=${project.id}">
                                    ${activity.conges}
                                </a>
                            </td>
                            </c:if>