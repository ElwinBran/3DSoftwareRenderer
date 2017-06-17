/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.java.models.newmodels;

import java.util.ArrayList;
import java.util.List;
import main.java.models.threedee.Vector4f;
/**
 * 
 * @author Elwin Slokker
 * @author Benny Bobaganoosh
 */
public class IndexedModel
{
    private List<Vector4f> m_positions;
    private List<Vector4f> m_texCoords;
    private List<Vector4f> m_normals;
    private List<Vector4f> m_tangents;
    private List<Integer> m_indices;

    public IndexedModel()
    {
        m_positions = new ArrayList<Vector4f>();
        m_texCoords = new ArrayList<Vector4f>();
        m_normals = new ArrayList<Vector4f>();
        m_tangents = new ArrayList<Vector4f>();
        m_indices = new ArrayList<Integer>();
    }

    public void calcNormals()
    {
        for (int i = 0; i < m_indices.size(); i += 3)
        {
            int i0 = m_indices.get(i);
            int i1 = m_indices.get(i + 1);
            int i2 = m_indices.get(i + 2);

            Vector4f v1 = m_positions.get(i1).subtract(m_positions.get(i0));
            Vector4f v2 = m_positions.get(i2).subtract(m_positions.get(i0));

            Vector4f normal = v1.crossProduct(v2).normalized();

            m_normals.set(i0, m_normals.get(i0).add(normal));
            m_normals.set(i1, m_normals.get(i1).add(normal));
            m_normals.set(i2, m_normals.get(i2).add(normal));
        }

        for (int i = 0; i < m_normals.size(); i++)
        {
            m_normals.set(i, m_normals.get(i).normalized());
        }
    }

    public void calcTangents()
    {
        for (int i = 0; i < m_indices.size(); i += 3)
        {
            int i0 = m_indices.get(i);
            int i1 = m_indices.get(i + 1);
            int i2 = m_indices.get(i + 2);

            Vector4f edge1 = m_positions.get(i1).subtract(m_positions.get(i0));
            Vector4f edge2 = m_positions.get(i2).subtract(m_positions.get(i0));

            float deltaU1 = m_texCoords.get(i1).getX() - m_texCoords.get(i0).getX();
            float deltaV1 = m_texCoords.get(i1).getY() - m_texCoords.get(i0).getY();
            float deltaU2 = m_texCoords.get(i2).getX() - m_texCoords.get(i0).getX();
            float deltaV2 = m_texCoords.get(i2).getY() - m_texCoords.get(i0).getY();

            float dividend = (deltaU1 * deltaV2 - deltaU2 * deltaV1);
            float f = dividend == 0 ? 0.0f : 1.0f / dividend;

            Vector4f tangent = new Vector4f(
                    f * (deltaV2 * edge1.getX() - deltaV1 * edge2.getX()),
                    f * (deltaV2 * edge1.getY() - deltaV1 * edge2.getY()),
                    f * (deltaV2 * edge1.getZ() - deltaV1 * edge2.getZ()),
                    0);

            m_tangents.set(i0, m_tangents.get(i0).add(tangent));
            m_tangents.set(i1, m_tangents.get(i1).add(tangent));
            m_tangents.set(i2, m_tangents.get(i2).add(tangent));
        }

        for (int i = 0; i < m_tangents.size(); i++)
        {
            m_tangents.set(i, m_tangents.get(i).normalized());
        }
    }

    public List<Vector4f> getPositions()
    {
        return m_positions;
    }

    public List<Vector4f> getTexCoords()
    {
        return m_texCoords;
    }

    public List<Vector4f> getNormals()
    {
        return m_normals;
    }

    public List<Vector4f> getTangents()
    {
        return m_tangents;
    }

    public List<Integer> getIndices()
    {
        return m_indices;
    }
}
