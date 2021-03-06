/*
    Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
    except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
    the specific language governing permissions and limitations under the License.
 */

package com.amazon.speech.speechlet.dialog.directives;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.ConfirmationStatus;
import com.amazon.speech.slu.Slot;

/**
 * Used by a skill to update the intent name and/or one or more slot values during a multi-turn dialog.
 *
 * @see DialogDirective#setUpdatedIntent
 */
public class DialogIntent {

    private String name;

    private ConfirmationStatus confirmationStatus;

    private Map<String, DialogSlot> slots;

    public DialogIntent(final String name, final ConfirmationStatus confirmationStatus, final Map<String, DialogSlot> slots) {
        this.name = name;
        this.confirmationStatus = confirmationStatus;
        this.slots = slots == null ? null : new HashMap<>(slots);
    }

    public DialogIntent(final String name, final ConfirmationStatus confirmationStatus) {
        this(name, confirmationStatus, null);
    }

    public DialogIntent(final String name) {
        this(name, null, null);
    }

    public DialogIntent(final String name, final Map<String, DialogSlot> slots) {
        this(name, null, slots);
    }

    public DialogIntent() {
        this(null, null, null);
    }

    public DialogIntent(final Intent requestIntent) {
        this(requestIntent.getName(), requestIntent.getConfirmationStatus(), convertSlots(requestIntent.getSlots()));
    }

    private static Map<String, DialogSlot> convertSlots(final Map<String, Slot> requestSlots) {
        Map<String, DialogSlot> slots = new HashMap<>();
        for (Entry<String, Slot> entry: requestSlots.entrySet()) {
            slots.put(entry.getKey(), new DialogSlot(entry.getValue()));
        }

        return slots;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ConfirmationStatus getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(final ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public Map<String, DialogSlot> getSlots() {
        return slots;
    }

    public void setSlots(final Map<String, DialogSlot> slots) {
        this.slots = slots == null ? null : new HashMap<>(slots);
    }

}
