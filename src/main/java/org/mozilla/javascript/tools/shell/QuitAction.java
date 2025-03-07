/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;

/**
 * Defines action to perform in response to quit command.
 */
public interface QuitAction {
    void quit(Context cx, int exitCode);
}

